package pl.javastart.equipy.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import pl.javastart.equipy.dto.DeviceDTO;
import pl.javastart.equipy.model.Device;
import pl.javastart.equipy.service.DeviceService;

import java.util.List;

@RestController
@RequestMapping("/api/assets")
public class DeviceController {

    private DeviceService deviceService;

    public DeviceController(DeviceService deviceService) {
        this.deviceService = deviceService;
    }

    @GetMapping("")
    List<DeviceDTO> findAll(@RequestParam(required = false) String name, @RequestParam(required = false) String serialNumber){
        if (name == null && serialNumber == null)
        return deviceService.findAll();
        else
            return deviceService.findAllByNameOrSerialNumberContaining(name, serialNumber);
    }

    @PostMapping("/asset-add")
    ResponseEntity<DeviceDTO> saveDevice(@PathVariable Long id,@RequestBody DeviceDTO deviceDTO){
        if (!id.equals(deviceDTO.getId()))
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Id uzytkownika jest rozne od id sciezki");
        else {
            DeviceDTO dto = deviceService.saveNewDevice(deviceDTO);
            return ResponseEntity.ok(dto);
        }
    }

    @PostMapping("")
    ResponseEntity<DeviceDTO> updateDevice(@RequestBody DeviceDTO deviceDTO){
        if (deviceDTO.getId() != null){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Zapisywany obiekt nie może mieć ustawionego id");
        }else {
            DeviceDTO dto = deviceService.saveNewDevice(deviceDTO);
            return ResponseEntity.ok(dto);
        }
    }
}
