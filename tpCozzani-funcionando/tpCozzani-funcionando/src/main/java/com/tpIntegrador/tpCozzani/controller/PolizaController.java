package com.tpIntegrador.tpCozzani.controller;

import com.tpIntegrador.tpCozzani.dtos.PolizaDTOS;
import com.tpIntegrador.tpCozzani.dtos.PolizaResponseDTOS;
import com.tpIntegrador.tpCozzani.modelo.Poliza;
import com.tpIntegrador.tpCozzani.service.PolizaService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Validated
@RestController
@RequestMapping("/polizas")


public class PolizaController {

    @Autowired
    private PolizaService polizaService;

    @PostMapping("/crear")
    public ResponseEntity <PolizaResponseDTOS> crearPoliza(@Valid @RequestBody PolizaDTOS polizaDTOS){
        PolizaResponseDTOS nuevaPoliza = polizaService.crearPoliza(polizaDTOS);
        return new ResponseEntity<>(nuevaPoliza, HttpStatus.CREATED);
    }

    @GetMapping("/")
    public List<PolizaResponseDTOS> listarPolizas(){
        return polizaService.listarPolizas();
    }

    @PutMapping("/actualizar/{id}")
    public ResponseEntity<PolizaResponseDTOS> actualizarPoliza(@PathVariable Long id, @Valid @RequestBody PolizaDTOS polizaDTOS){
        PolizaResponseDTOS polizaActualizada = polizaService.actualizarPoliza(id,polizaDTOS);
        return ResponseEntity.ok(polizaActualizada);
    }

    @DeleteMapping("/delete/{id}")
    public void eliminarPoliza(@PathVariable Long id){
        polizaService.eliminarPoliza(id);

    }
}
