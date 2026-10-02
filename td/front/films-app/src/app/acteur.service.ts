import { Injectable, inject } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Acteur } from './acteur.model';
import { Film } from './film.model';
import { Observable, catchError, of } from 'rxjs';

@Injectable({ providedIn: 'root'})
export class ActeurService {
    private http = inject(HttpClient);
    private url = '/api/acteurs';
    getAll() : Observable<Acteur[]>{
        return this.http.get<Acteur[]>(this.url).pipe(
            catchError(() => of([]))
        );
    }
    getById(id: number): Observable<Acteur>{
        return this.http.get<Acteur>(`${this.url}/${id}`);
    }

    getFilms(acteurId: number): Observable<Film[]> {
        return this.http.get<Film[]>(`${this.url}/${acteurId}/films`);
    }
}