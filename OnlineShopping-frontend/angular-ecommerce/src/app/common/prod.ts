export class Prod {

    constructor(
        public product_id : number,  
        public name: string,
        public description: string,      
        public retail_price: number,
        public quantity: number,
        public whole_price: number
   )
    {
   }

   public getName():string{
     return this.name;
   }

}
