package pl.javastart.equipy.service;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.dto.DeviceDTO;
import pl.javastart.equipy.exception.DuplicateSerialNumberException;
import pl.javastart.equipy.mapper.DeviceMapper;
import pl.javastart.equipy.model.Device;
import pl.javastart.equipy.repository.DeviceRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class DeviceService {

    private DeviceRepository deviceRepository;
    private DeviceMapper deviceMapper;

    public DeviceService(DeviceRepository deviceRepository, DeviceMapper deviceMapper) {
        this.deviceRepository = deviceRepository;
        this.deviceMapper = deviceMapper;
    }

    public List<DeviceDTO> findAll(){
        return deviceRepository
                .findAll()
                .stream()
                .map(DeviceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public List<DeviceDTO> findAllByNameOrSerialNumberContaining(String name, String serialNumber){
        return deviceRepository
                .findAllByNameContainingIgnoreCaseOrSerialNumberContainingIgnoreCase(name, serialNumber)
                .stream()
                .map(DeviceMapper::toDTO)
                .collect(Collectors.toList());
    }

    public DeviceDTO saveNewDevice(DeviceDTO deviceDTO){
        Device device = DeviceMapper.toDevice(deviceDTO);
        Device save = deviceRepository.save(device);
        return DeviceMapper.toDTO(save);
    }

    public DeviceDTO save(DeviceDTO deviceDTO){
        Optional<Device> bySerialNumber = deviceRepository.findBySerialNumber(deviceDTO.getSerialNumber());
        bySerialNumber.ifPresent(d -> {
            throw new DuplicateSerialNumberException("Podany numer serii jest juz zajety");
        });
        Device device = deviceMapper.toDevice(deviceDTO);
        Device save = deviceRepository.save(device);
        return DeviceMapper.toDTO(save);
    }
}
