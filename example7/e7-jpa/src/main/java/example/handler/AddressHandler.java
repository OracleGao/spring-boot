package example.handler;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.persistence.criteria.Predicate;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import example.entity.Address;
import example.repository.AddressRepository;

@RestController
@RequestMapping(path = "/addresses", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressHandler {
	
	@Autowired
	private AddressRepository addressRepository;
	
	@RequestMapping(method = RequestMethod.GET)
	public Page<Address> onGetAddress(@RequestParam Map<String, String> map, Pageable pageable) {
		Page<Address> addresses = addressRepository.findAll((root, criteriaQuery, criteriaBuilder) -> {
			List<Predicate> list = new ArrayList<Predicate>();
			if (map.containsKey("ageLeft")) {
				list.add(criteriaBuilder.greaterThanOrEqualTo(root.get("student").get("age").as(Integer.class), new Integer(map.get("ageLeft"))));
			}
			if (map.containsKey("ageRight")) {
				list.add(criteriaBuilder.lessThanOrEqualTo(root.get("student").get("age").as(Integer.class), new Integer(map.get("ageRight"))));
			}
			if (map.containsKey("name")) {
				list.add(criteriaBuilder.like(root.get("student").get("name"), "%" + map.get("name") + "%"));
			}
			if (map.containsKey("sex")) {
				list.add(criteriaBuilder.equal(root.get("student").get("sex"), new Integer(map.get("sex"))));
			}
			if (map.containsKey("address")) {
				list.add(criteriaBuilder.like(root.get("address"), "%" + map.get("address") + "%"));
			}
			if (list.size() > 1) {
				return criteriaBuilder.and(list.toArray(new Predicate[list.size()]));
			} else if (list.size() == 1) {
				return list.get(0);
			} else {
				return null;
			}
		}, pageable);
		addresses.forEach(address -> address.breakBidirectionalRelationship());
		return addresses;
	}
	
}
