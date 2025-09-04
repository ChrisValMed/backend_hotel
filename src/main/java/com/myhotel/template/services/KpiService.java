package com.myhotel.template.services;

import com.myhotel.template.models.WeightedKpiResult;
import com.myhotel.template.projections.SurveyScoreGroupProjection;
import com.myhotel.template.repositories.SurveyResultRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Service
public class KpiService {

    private final SurveyResultRepository repository;

    public KpiService(SurveyResultRepository repository){
        this.repository = repository;
    }

    public List<WeightedKpiResult<String, Double>> getWeightedAverageScore(){
        List<SurveyScoreGroupProjection> groupedScores = repository.findGroupedScoreData();
        Map<String, List<SurveyScoreGroupProjection>> groupedByHotel = groupByHotelName(groupedScores);

        return groupedByHotel.entrySet().stream()
                .map(entry -> calculateWeightedAverage(entry.getKey(), entry.getValue()))
                .toList();
    }

    private Map<String, List<SurveyScoreGroupProjection>> groupByHotelName(List<SurveyScoreGroupProjection> groupedScores){
        //Se Agrupa por nombre del hotel es más directo y evita el 'findAny()' posterior.
        return groupedScores.stream()
                .collect(Collectors.groupingBy(SurveyScoreGroupProjection::getHotelName));
    }

    /**
     * Calcula el promedio ponderado de las puntuaciones para un hotel dado.
     * La fórmula es: weightedAverage = Σ(score * count) / Σ(count)
     */
    private WeightedKpiResult<String, Double> calculateWeightedAverage(String hotelName, List<SurveyScoreGroupProjection> projections){
        // El cálculo se simplifica.
        // El numerador es la suma de cada (puntuación * su frecuencia).
        double numerator = projections.stream()
                .mapToDouble(SurveyScoreGroupProjection::weightedScore)
                .sum();

        // El denominador es el número total de encuestas (la suma de todas las frecuencias).
        double denominator = projections.stream()
                .mapToLong(SurveyScoreGroupProjection::getScoreCount)
                .sum();

        // Evitar división por cero.
        double weightedAvg = (denominator == 0) ? 0.0 : numerator / denominator;

        return new WeightedKpiResult<>(hotelName, weightedAvg);
    }
}