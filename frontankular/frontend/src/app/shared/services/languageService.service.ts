import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders } from '@angular/common/http'

import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { MessageService } from './message.service';
import { Language } from 'src/app/shared/models/language';

@Injectable({ providedIn: 'root' })
export class LanguageService {

  private languagesUrl = 'http://localhost:8080/langages';

  httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json' })
  };

  constructor(
    private http: HttpClient,
    private messageService: MessageService
  ) { }

  getLanguages(): Observable<Language[]> {
    return this.http.get<Language[]>(`${this.languagesUrl}`)
      .pipe(
        tap(_ => this.log('fetched languages')),
        catchError(this.handleError<Language[]>('getLanguages', []))
      );
  }

  //  /** GET language by id. Return `undefined` when id not found */
  //  getLanguageNo404<Data>(id: number): Observable<Language> {
  //   const url = `${this.languagesUrl}/${id}`;
  //   return this.http.get<Language[]>(url)
  //     .pipe(
  //       map(languages => languages[0]), // returns a {0|1} element array
  //       tap(h => {
  //         const outcome = h ? `fetched` : `did not find`;
  //         this.log(`${outcome} langage id=${id}`);
  //       }),
  //       catchError(this.handleError<Language>(`getLangage id=${id}`))
  //     );
  // }

  // /** GET language by id. Will 404 if id not found */
  // getLanguage(id: number): Observable<Language> {
  //   const url = `${this.languagesUrl}/${id}`;
  //   return this.http.get<Language>(url).pipe(
  //     tap(_ => this.log(`fetched language id=${id}`)),
  //     catchError(this.handleError<Language>(`getLanguage id=${id}`))
  //   );
  // }

  // /* GET language whose name contains search term */
  // searchLanguages(term: string): Observable<Language[]> {
  //   if (!term.trim()) {
  //     // if not search term, return empty hero array.
  //     return of([]);
  //   }
  //   return this.http.get<Language[]>(`${this.languagesUrl}/find?name=${term}`).pipe(
  //     tap(_ => this.log(`found languages matching "${term}"`)),
  //     catchError(this.handleError<Language[]>('searchLanguages', []))
  //   );
  // }



  addLanguage(language: Language): Observable<Language> {
    return this.http.post<Language>(this.languagesUrl, language, this.httpOptions);
  }

  deleteLanguage(language: Language | number): Observable<Language> {
    const id = typeof language === 'number' ? language : language.id;
    return this.http.delete<Language>(this.languagesUrl, this.httpOptions).pipe(
      tap(_ => this.log(`deleted language id=${id}`)),
      catchError(this.handleError<Language>('deleteLanguage'))
      );
  }

  // /** PUT: update the hero on the server */
  // updateLanguage(language: Language): Observable<any> {
  //   return this.http.put(this.languagesUrl, language, this.httpOptions).pipe(
  //     tap(_ => this.log(`updated language id=${language.id}`)),
  //     catchError(this.handleError<any>('updateLanguage'))
  //   );
  // }

  private handleError<T>(operation = 'operation', result?: T) {
    return (error: any): Observable<T> => {

      console.error(error);
      this.log(`${operation} failed: ${error.message}`);
      return of(result as T);
    };
  }

  private log(message: string) {
    this.messageService.add(`LanguageService: ${message}`);
  }

}
