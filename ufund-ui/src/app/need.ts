export interface Need {
    name: string;
    cost: number;  // Renamed from 'price' to match Java model
    currentQuantity: number;  // Matches Java model
    requiredQuantity: number;  // Matches Java model
    type: string;
    requiredquantity: number;
}