package pl.javastart.equipy.mapper;

import org.springframework.stereotype.Service;
import pl.javastart.equipy.dto.DeviceDTO;
import pl.javastart.equipy.model.Device;
import pl.javastart.equipy.repository.DeviceRepository;

import java.util.Optional;

@Service
public class DeviceMapper {

    private static DeviceRepository deviceRepository;

    public DeviceMapper(DeviceRepository deviceRepository) {
        this.deviceRepository = deviceRepository;
    }

     public static DeviceDTO toDTO(Device device){
        DeviceDTO deviceDTO = new DeviceDTO();
        deviceDTO.setId(device.getId());
        deviceDTO.setName(device.getName());
        deviceDTO.setDescription(device.getDescription());
        deviceDTO.setSerialNumber(device.getSerialNumber());
        if (device.getCategory() != null){
            deviceDTO.setCategory(device.getCategory().getName());
        }
        return deviceDTO;
    }

      public static Device toDevice(DeviceDTO deviceDTO){
        Device device = new Device();
        device.setId(deviceDTO.getId());
        device.setName(deviceDTO.getName());
        device.setDescription(deviceDTO.getDescription());
         Optional<Device> byName = deviceRepository.findByName(deviceDTO.getCategory());
         byName.ifPresent(device::setCategory);
         device.setSerialNumber(deviceDTO.getSerialNumber());
         return device;
     }
}
