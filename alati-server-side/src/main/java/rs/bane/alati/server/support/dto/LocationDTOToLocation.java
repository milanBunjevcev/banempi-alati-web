package rs.bane.alati.server.support.dto;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;
import org.springframework.stereotype.Component;

import rs.bane.alati.server.model.Location;
import rs.bane.alati.server.service.LocationService;
import rs.bane.alati.server.web.dto.LocationDTO;

@Component
public class LocationDTOToLocation implements Converter<LocationDTO, Location> {

	@Autowired
	private LocationService locationService;

	@Override
	public Location convert(LocationDTO dto) {

		if (dto == null) {
			return null;
		}

		Location location = null;
		if (dto.getId() != null) {
			location = locationService.findOne(dto.getId());
		} else {
			location = new Location();
		}

		location.setId(dto.getId());
		location.setName(dto.getName());

		return location;

	}

}
