/**
 * corresponds aux joueurs et administrateurs
 */
export class User{
    id: number;
    date_inscription: Date;
    email: string;
    password: string;
    username: string;
    type_user_id: number;
    ranking: number;
}