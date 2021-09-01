package pl.javastart.equipy.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import pl.javastart.equipy.model.Device;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends JpaRepository<Device, Long> {
    Optional<Device> findByName(String name);
    List<Device> findAllByNameContainingIgnoreCaseOrSerialNumberContainingIgnoreCase(String name, String SerialNumber);
    Optional<Device> findBySerialNumber(String serialNumber);
}
