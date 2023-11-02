package io.kianyanglee.tacos.services;

import org.springframework.boot.actuate.info.Info.Builder;
import org.springframework.stereotype.Component;

import io.kianyanglee.tacos.repositories.TacoRepository;

import java.util.HashMap;
import java.util.Map;

import org.springframework.boot.actuate.info.InfoContributor;

@Component
public class TacoCountInfoContributor implements InfoContributor {

    private TacoRepository tacoRepository;

    public TacoCountInfoContributor(TacoRepository tacoRepository) {
        this.tacoRepository = tacoRepository;
    }

    @Override
    public void contribute(Builder builder) {
        long tacoCount = tacoRepository.count();
        Map<String, Object> tacoMap = new HashMap<>();
        tacoMap.put("count", tacoCount);
        builder.withDetail("taco-stats", tacoMap);
    }

}
