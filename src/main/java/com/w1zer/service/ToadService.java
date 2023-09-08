package com.w1zer.service;

import com.w1zer.entity.Toad;
import com.w1zer.repository.ToadRepository;

import java.util.Collection;
import java.util.Objects;

public class ToadService {

    private final ToadRepository toadRepository;

    public ToadService(ToadRepository toadRepository) {
        this.toadRepository = toadRepository;
    }

    public Collection<Toad> getToadList(Long idProfile) {
        return toadRepository.getByIdProfile(idProfile);
    }

    public void addToad(Toad toad) {
        toadRepository.insert(toad);
    }

    public boolean canModify(Long idProfile, Long idToad) {
        Toad toad = toadRepository.getById(idToad);
        if (toad == null) {
            return false;
        }
        return Objects.equals(toad.getIdProfile(), idProfile);
    }

    public void deleteToad(Long id) {
        toadRepository.delete(id);
    }

    public Toad getToad(Long id) {
        return toadRepository.getById(id);
    }

    public void editToad(Toad toad) {
        toadRepository.update(toad);
    }

}
