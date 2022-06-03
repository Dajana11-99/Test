package rs.ac.uns.ftn.isa.fisherman.service;

import rs.ac.uns.ftn.isa.fisherman.model.AdditionalServices;

import java.util.List;
import java.util.Set;

public interface AdditionalServicesService {
    List<AdditionalServices> getAll();
    AdditionalServices findById(Long id);
    void delete(Set<AdditionalServices> additionalServices);
    Set<AdditionalServices> findDeletedAdditionalServices(Set<AdditionalServices> oldServices,Set<AdditionalServices> newServices);
}
