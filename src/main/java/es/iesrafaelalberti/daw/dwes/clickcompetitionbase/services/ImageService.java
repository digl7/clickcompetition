package es.iesrafaelalberti.daw.dwes.clickcompetitionbase.services;

import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Player;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.model.Team;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.PlayerRepository;
import es.iesrafaelalberti.daw.dwes.clickcompetitionbase.repositories.TeamRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.persistence.EntityNotFoundException;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

@Service
public class ImageService {
    @Autowired
    private PlayerRepository playerRepository;

    @Autowired
    private TeamRepository teamRepository;

    public boolean imagePlayerStore (MultipartFile file, Long id) throws IOException {
        String fileName = id.toString() + "_" + file.getOriginalFilename();
        Path targetPath = Paths.get(".\\images\\players\\"+fileName).normalize(); //EN UN PROYECTO SPRING TOMA POR DEFECTO LA CARPETA A LA ALTURA DEL SRC
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        Player player = playerRepository.findPlayerById(id).
                orElseThrow(() -> new EntityNotFoundException(id.toString()));
        player.setImageUrl("/download/players/"+fileName);
        playerRepository.save(player);
        return false;
    }

    public boolean imageTeamStore (MultipartFile file, Long id) throws IOException {
        String fileName = id.toString() + "_" + file.getOriginalFilename();
        Path targetPath = Paths.get(".\\images\\teams\\"+fileName).normalize(); //EN UN PROYECTO SPRING TOMA POR DEFECTO LA CARPETA A LA ALTURA DEL SRC
        Files.copy(file.getInputStream(), targetPath, StandardCopyOption.REPLACE_EXISTING);
        Team team = teamRepository.findTeamById(id).
                orElseThrow(() -> new EntityNotFoundException(id.toString()));
        team.setImageUrl("/download/teams/"+fileName);
        teamRepository.save(team);
        return false;
    }

}
