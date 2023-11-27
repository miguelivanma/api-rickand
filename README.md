# Rest API
## Overview
Run an endpoint(http://localhost:8090/search-character-appearance?name=Rick) that consumes a REST/JSON API (https://rickandmortyapi.com), searching by name
and returns all the names of the episodes in which that character appears and the date of their first appearance in a series.
## Guidelines
Run the Rest API
1. Clone the Project, run the following:
```
git clone https://github.com/miguelivanma/api-rickand
```
2. Create jar, run the following:
```
mvn clean install
```
3. Run microservice, run the following:
```
mvn spring-boot:run
```
4. Execute End Point<br>
Run the following URL in a browser: http://localhost:8090/swagger-ui/index.html <br>
Choosing the End Point GET /search-character-appearance<br> 
Press the button 'Try it out'<br>
Enter the name : Rick<br> 
Press the button 'Execute'<br>

5. Create imagen Docker
```
mvn clean package
```
```
docker build -t api-1.0.1 .
```
6. Start the container
```
docker run api-1.0.1
```
## Source Code Review
The source code is located in /src/main/java/com/rickand/controller/SearchCharacterController.java.The followings overview of how it communicates with the REST/JSON API (https://rickandmortyapi.com).
The controller invokes the SearchCharacterAppearanceService.java class through the getCharacter(@RequestParam String name) method. Sending as @RequestParam the character's name.
```java
@RestController
@RequestMapping
@RequiredArgsConstructor
public class SearchCharacterController {
    private final SearchCharacterAppearanceService searchCharacterAppearanceService;
    
    @GetMapping("/search-character-appearance")
    public ResponseEntity<CharacterAppearance> getCharacter(@RequestParam String name) throws CharacterNotFoundException {
        return new ResponseEntity<>(searchCharacterAppearanceService.getCharacterAppearance(name), HttpStatus.OK);
    }
}
```
The source code /src/main/java/com/rickand/service/impl/ClientSearchCharacter.java is to get the characters and /src/main/java/com/rickand/service/impl/ClientSearchEpisode.java is to get the episodes that is the information to display.
```java
@Service
@RequiredArgsConstructor
public class SearchCharacterAppearanceService {
    private final SearchCharacterService searchCharacterService;
    private final SearchEpisodeService searchEpisodeService;
}
```
The SearchCharacterService.java class invokes the ClientSearchCharacter.java client interface.
```java
@Service
@RequiredArgsConstructor
public class SearchCharacterService {
    private final ClientSearchCharacter clientSearchCharacter;
}
```
The SearchEpisodeService.java class invokes the ClientSearchEpisode.java client interface.
```java
@Service
@RequiredArgsConstructor
public class SearchEpisodeService {
    private final ClientSearchEpisode clientSearchEpisode;
}
```
The ClientSearchCharacter client interface will invoke the url = "${url.service}") of the Resp API. Which will be defined in application.properties. Sending as @RequestParam the character's name.
```java
@FeignClient(name = "ClientSearchCharacter", url = "${url.service}", configuration = FeignClientConfig.class)
public interface ClientSearchCharacter {
    @GetMapping(value="/character", consumes = MediaType.APPLICATION_JSON_VALUE)
    Character getCharacter(@RequestParam String name);
}
```
The ClientSearchEpisode.java client interface will invoke the url = "${url.service}") of the Resp API. Which will be defined in application.properties. Sending as @PathVariable the episode id. For all episodes associated with the character.
```java
@FeignClient(name = "ClientSearchEpisode", url = "${url.service}",configuration = FeignClientConfig.class)
public interface ClientSearchEpisode {
    @GetMapping(value="/episode/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    Episode getEpisode(@PathVariable String id);
}
```
From all the episodes, the date of their first appearance is obtained.
```
    private String getDateFirstAppearance(List<Episode> episodesList) {
        return episodesList.stream()
                .sorted((x, y) -> Utils.ConvertStringToDate(x.getAirDate()).compareTo(Utils.ConvertStringToDate(y.getAirDate())))
                .limit(1)
                .collect(Collectors.toList()).get(0).getAirDate();
    }
```

Get the name of all the episodes.
```
    private ArrayList<String> getListNameEpisode(ArrayList<Episode> episodesList) {
        ArrayList<String> nameEpisodes = new ArrayList<String>();
        episodesList.stream().map(x -> x.getName()).forEach(x -> nameEpisodes.add(x));
        return nameEpisodes;
    }
```
Finally all the information is mapped into the CharacterAppearance.java class and returned as a response to the services and then to the controller.
```
     var dateFirstAppearance = this.getDateFirstAppearance(episodesList);
     var nameEpisodes = this.getListNameEpisode(episodesList);
     characterAppearance.setName(results.get(0).getName());
     characterAppearance.setEpisodes(nameEpisodes);
     characterAppearance.setFirstApppearance(dateFirstAppearance);
     return characterAppearance;
```