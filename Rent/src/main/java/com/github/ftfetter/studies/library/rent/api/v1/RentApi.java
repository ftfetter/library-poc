package com.github.ftfetter.studies.library.rent.api.v1;

import com.github.ftfetter.studies.library.rent.exception.InternalServerErrorException;
import com.github.ftfetter.studies.library.rent.service.RentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

@RequestMapping("/v1/rents")
public class RentApi {

    private RentService rentService;

    @Autowired
    public RentApi(RentService rentService) {
        this.rentService = rentService;
    }

    @PostMapping
    public ResponseEntity<?> registerRent(@RequestParam String userId, @RequestParam String bookId) {
        try {
            return ResponseEntity.ok(rentService.rent(userId, bookId));
        } catch (RuntimeException re) {
            throw re;
        } catch (Exception e) {
            throw new InternalServerErrorException(e.getMessage());
        }
    }
}
