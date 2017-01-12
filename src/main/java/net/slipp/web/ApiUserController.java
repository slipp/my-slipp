package net.slipp.web;

import java.net.URI;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import net.slipp.domain.User;
import net.slipp.domain.UserRepository;

@RestController
@RequestMapping("/api/users")
public class ApiUserController {
	@Autowired
	private UserRepository userRepository;
	
	@GetMapping("/{id}")
	public ResponseEntity<User> show(@PathVariable Long id) {
		return ResponseEntity.ok(userRepository.findOne(id));
	}
	
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody User user) throws Exception {
		User result = userRepository.save(user);
		return ResponseEntity.created(new URI("/api/users/" + result.getId())).build();
	}
}
