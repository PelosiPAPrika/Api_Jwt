package com.jwt.test.demo.api;

import com.jwt.test.demo.domain.TbTable;
import com.jwt.test.demo.service.TableService;
import lombok.NonNull;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Log4j2
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/table")
@CrossOrigin(origins = "*")
public class TableController {

    @Autowired
    private SimpMessagingTemplate template;
    private final @NonNull TableService tableService;

    @GetMapping("/{tableNumber}")
    public ResponseEntity<TbTable> findByTableNumber(@PathVariable String tableNumber){
        return ResponseEntity.ok(tableService.findByTableNumber(tableNumber));
    }

    @GetMapping("/{id}")
    public ResponseEntity<TbTable> findById(@PathVariable Long id){
        return ResponseEntity.ok(tableService.findTableByIdOrThrowBadRequest(id).get());
    }

    @GetMapping("/all")
    public ResponseEntity<List<TbTable>> findAll(){
        return ResponseEntity.ok(tableService.getTables());
    }

    @PostMapping(value = "/save")
    public ResponseEntity<TbTable> saveTable(@RequestBody TbTable table){
        return new ResponseEntity<>(tableService.save(table), HttpStatus.CREATED);
    }

    @PutMapping("/occupy/{id}")
    public ResponseEntity<Long> occupyTable(@PathVariable Long id){
        template.convertAndSend("/topic/table", tableService.findTableByIdOrThrowBadRequest(id).get());
        return ResponseEntity.ok().body(tableService.occupyTable(id));
    }

    @PutMapping("/leave/{id}")
    public ResponseEntity<Void> leaveTable(@PathVariable Long id){
        template.convertAndSend("/topic/table", tableService.findTableByIdOrThrowBadRequest(id).get());
        tableService.leaveTable(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @MessageMapping("/sendTable")
    public void receiveTable(@Payload TbTable table){

    }

    @SendTo("/topic/table")
    public TbTable broadcastTable(@Payload TbTable table){
        return table;
    }
}
