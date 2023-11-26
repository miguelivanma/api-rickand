package com.rickand.service;

import com.rickand.exception.CharacterNotFoundException;
import com.rickand.exception.EpisodeNotFoundException;
import com.rickand.model.CharacterAppearance;
import com.rickand.model.Episode;
import com.rickand.model.Result;
import com.rickand.util.Utils;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * The type Search character appearance service.
 */
@Service
@RequiredArgsConstructor
public class SearchCharacterAppearanceService {
    private final SearchCharacterService searchCharacterService;
    private final SearchEpisodeService searchEpisodeService;

    /**
     * Gets character appearance.
     *
     * @param name the name
     * @return the character appearance
     */
    public CharacterAppearance getCharacterAppearance(String name) {
            var character=searchCharacterService.getCharacter(name);
            return this.getCharacterAppearances(character.getResults());
    }

    private CharacterAppearance getCharacterAppearances(ArrayList<Result> results) {
        try {
            CharacterAppearance characterAppearance = CharacterAppearance.builder().build();
            ArrayList<String> listResutl = new ArrayList<String>();
            results.stream().map(x -> x.getEpisode()).forEach(x -> x.forEach(y -> listResutl.add(y)));
            var episodesList = this.getEpisode(listResutl);
            var dateFirstAppearance = this.getDateFirstAppearance(episodesList);
            var nameEpisodes = this.getListNameEpisode(episodesList);
            characterAppearance.setName(results.get(0).getName());
            characterAppearance.setEpisodes(nameEpisodes);
            characterAppearance.setFirstApppearance(dateFirstAppearance);
            return characterAppearance;
        } catch (EpisodeNotFoundException ex) {
            throw new EpisodeNotFoundException();
        }catch (Exception ex){
            throw new CharacterNotFoundException((results!=null)?results.get(0).getName():"");
        }
    }

    private ArrayList<Episode> getEpisode(ArrayList<String> episodes) {
        try {
        ArrayList<Episode> episodesList = new ArrayList<>();
        episodes.forEach(episode -> {
            String[] part = episode.split("/");
            var episodeAux = searchEpisodeService.getEpisode(part[part.length - 1]);
            episodeAux.setAirDate(Utils.formatDate(episodeAux.getAirDate()));
            episodesList.add(episodeAux);
        });
        return episodesList;
        } catch (Exception ex) {
            throw new EpisodeNotFoundException();
        }
    }

    private String getDateFirstAppearance(List<Episode> episodesList) {
        return episodesList.stream()
                .sorted((x, y) -> Utils.ConvertStringToDate(x.getAirDate()).compareTo(Utils.ConvertStringToDate(y.getAirDate())))
                .limit(1)
                .collect(Collectors.toList()).get(0).getAirDate();
    }

    private ArrayList<String> getListNameEpisode(ArrayList<Episode> episodesList) {
        ArrayList<String> nameEpisodes = new ArrayList<String>();
        episodesList.stream().map(x -> x.getName()).forEach(x -> nameEpisodes.add(x));
        return nameEpisodes;
    }
}
