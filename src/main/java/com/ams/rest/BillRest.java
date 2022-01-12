package com.ams.rest;

import com.ams.service.BillService;
import org.apache.commons.io.IOUtils;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
@RequestMapping("/bill")
public class BillRest {

    private final BillService billService;

    public BillRest(BillService billService) {
        this.billService = billService;
    }

    @GetMapping(value = "/{id}", produces = MediaType.APPLICATION_PDF_VALUE)
    public @ResponseBody byte[] getReport(@PathVariable int id) throws IOException {
        return billService.getBill(id);
    }
}
