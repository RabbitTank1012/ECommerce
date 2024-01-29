import { Prod } from './prod';

export class Cart {

    id: number;
    name: string;
    retail_price: number;
    quantity: number;

    constructor(product: Prod) {
        this.id = product.product_id;
        this.name = product.name;
        this.retail_price = product.retail_price;
        this.quantity = 1;
    }

    // constructor(id: number) {
    //     this.id =id;
    // }
}
