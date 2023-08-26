package com.atacado.presentes.api.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.atacado.presentes.api.model.ItensDoPedido;
import com.atacado.presentes.api.repository.ItensDoPedidoRepository;

@RestController
@RequestMapping(value = "/itensDoPedido")
public class ItensDoPedidoController {

    @Autowired
    private ItensDoPedidoRepository itensDoPedidoRepository;

    @PostMapping
    public ItensDoPedido cadastrarItemDoPedido(@RequestBody ItensDoPedido itensDoPedido){
        return itensDoPedidoRepository.save(itensDoPedido);
    }

    @GetMapping
    public List<ItensDoPedido> listarItens(){
        return itensDoPedidoRepository.findAll();
    }


    @GetMapping("/{idItem}")
    public Optional<ItensDoPedido> buscarItemPeloId(@PathVariable("id")Long idItem){
        return itensDoPedidoRepository.findById(idItem);
    }

    @PutMapping("/{id}")
    public ItensDoPedido atualizarItem(
        @PathVariable("id")Long idItem,
        @RequestBody ItensDoPedido itensdoPedido){
            Optional<ItensDoPedido> itemExistente = itensDoPedidoRepository.findById(idItem);

            if (itemExistente.isPresent()) {
                itemExistente.get().setQuantidade(itensdoPedido.getQuantidade());
                return itensDoPedidoRepository.save(itemExistente.get());
            }
            return null;
        }

        @DeleteMapping("/idItem")
        public void deletarItemPeloId(@PathVariable("idItem")Long id){
            itensDoPedidoRepository.deleteById(id);
        }
    
}
