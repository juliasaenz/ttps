import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { FormsModule } from '@angular/forms';
import { Comida } from '../../models/comida.model';
import { ComidaService } from '../../services/comida.service';

@Component({
  selector: 'app-comidas',
  imports: [CommonModule, FormsModule],
  templateUrl: './comida.component.html',
  styleUrls: ['./comida.component.css']
})
export class ComidaComponent implements OnInit {
  comidas: Comida[] = [];

  newComida: Comida = {
    nombre: '',
    tipo: null,
    vegetariano: false
  };

  constructor(private comidaService: ComidaService) { }

  ngOnInit(): void {
    console.log('ComidaComponent: ngOnInit');
    this.getComidas();
  }

  getComidas(): void {
    console.log('ComidaComponent: getComidas');
    this.comidaService.getComidas().subscribe((data: Comida[]) => {
      console.log('ComidaComponent: getComidas: ', data);
      this.comidas = data;
    });
  }

  //TODO: Hacer funcionar con API
  addComida() {
    console.log('ComidaComponent: addComida');
    const isNameUnique = !this.comidas.find(
      comida => comida.nombre.toLowerCase() === this.newComida.nombre.toLowerCase()
    );

    if (isNameUnique) {
      console.log('ComidaComponent: addComida: isNameUnique');
      this.comidas.push({ ...this.newComida });
      this.newComida = { nombre: '', tipo: null, vegetariano: false };
    } else {
      console.log('ComidaComponent: addComida: not isNameUnique');
      alert('The name of the comida must be unique!');
    }
  }

  //TODO: hacer funcionar con api
  editComida(comida: Comida) {
    console.log('ComidaComponent: editComida');
    console.log(comida);
  }
}

