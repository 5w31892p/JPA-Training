/*
package me.woo.jpastudy.channel;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PagedResourcesAssembler;
import org.springframework.hateoas.PagedModel;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import me.woo.jpastudy.user.User;

@RestController
public class ChannelController {
	@Autowired
	ChannelRepository channelRepository;

	@GetMapping("/channels")
	public PagedModel<User> getUsers(Pageable pageable, PagedResourcesAssembler<User> assembler) {
		var all = channelRepository.findAll(pageable);
		return assembler.toModel(all);
	}
}
*/
