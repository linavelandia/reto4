/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.example.demo.Servicio;

import com.example.demo.Modelo.Motorbike;
import com.example.demo.Repositorio.MotorbikeRepositorio;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service

public class MotorbikeServicio {
    @Autowired
    private MotorbikeRepositorio motorbikeRepositorio;

    public List<Motorbike> getAll(){
        return motorbikeRepositorio.getAll();
    }

    public Optional<Motorbike> getMotorbike(int motorbikeId) {
        return motorbikeRepositorio.getMotorbike(motorbikeId);
    }

    public Motorbike save(Motorbike motorbike){
        if(motorbike.getId()==null){
            return motorbikeRepositorio.save(motorbike);
        }else{
            Optional<Motorbike>e= motorbikeRepositorio.getMotorbike(motorbike.getId());
            if(e.isEmpty()){
                return motorbikeRepositorio.save(motorbike);
            }else{
                return motorbike;
            }
        }
    }

    public Motorbike update(Motorbike motorbike){
        if(motorbike.getId()!=null){
            Optional<Motorbike> e= motorbikeRepositorio.getMotorbike(motorbike.getId());
            if(!e.isEmpty()){
                if(motorbike.getName()!=null){
                    e.get().setName(motorbike.getName());
                }
                if(motorbike.getBrand()!=null){
                    e.get().setBrand(motorbike.getBrand());
                }
                if(motorbike.getYear()!=null){
                    e.get().setYear(motorbike.getYear());
                }
                if(motorbike.getDescription()!=null){
                    e.get().setDescription(motorbike.getDescription());
                }
                if(motorbike.getCategory()==null){
                    e.get().setCategory(e.get().getCategory());
                }
                motorbikeRepositorio.save(e.get());
                return e.get();
            }else{
                return motorbike;
            }
        }else{
            return motorbike;
        }

    }

    public boolean deleteMotorbike(int id){
        Boolean d1 = getMotorbike(id).map(motorbike ->{
            motorbikeRepositorio.delete(motorbike);
            return true;
        }).orElse(false);
        return d1;
    }

}
