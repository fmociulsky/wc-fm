package examen.wenance.bitcoincotizacionapi.controller;

import examen.wenance.bitcoincotizacionapi.model.BitcoinValue;
import examen.wenance.bitcoincotizacionapi.service.BitcoinValueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.sql.Timestamp;
import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/cotizaciones")
public class BitcoinValueController {

    @Autowired
    BitcoinValueService bitcoinValueService;

    @GetMapping("")
    public ResponseEntity<?> getCotizacionPorFecha(@RequestParam(value = "fecha_exacta") String fecha_exacta){
        final Timestamp timestamp = Timestamp.valueOf(fecha_exacta);
        final Double value = bitcoinValueService.getCotizacionPorFechaStream(timestamp.toLocalDateTime());
        return new ResponseEntity<Double>(value, OK);
    }

    @GetMapping("/promedio")
    public ResponseEntity<?> getPromedioCotizacionEntreFechas(@RequestParam(value = "fecha_desde") String fecha_desde, @RequestParam(value = "fecha_hasta") String fecha_hasta){
        final Timestamp timestampDesde = Timestamp.valueOf(fecha_desde);
        final Timestamp timestampHasta = Timestamp.valueOf(fecha_hasta);
        final Double value = bitcoinValueService.getPromedioCotizacionEntreFechasStream(timestampDesde.toLocalDateTime(), timestampHasta.toLocalDateTime());
        return new ResponseEntity<Double>(value, OK);
    }

    @GetMapping("/registro")
    public ResponseEntity<List<BitcoinValue>> getFechas(){
        List<BitcoinValue> fechas = bitcoinValueService.getAllDates();
        return new ResponseEntity<List<BitcoinValue>>(fechas, OK);
    }


}
