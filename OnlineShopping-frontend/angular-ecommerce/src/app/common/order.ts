export class Order {
    constructor(
        public order_id : number,  
        public date_created: string,      
        public order_status: string,
        public user_id: number
   )
    {
   }
}
