export const OrderStatus = Object.freeze({
	INIT: Symbol(0),
	PROCESSING: Symbol(1),
	PAYED: Symbol(2),
	REFUND: Symbol(3),
	EXPIRE: Symbol(4),
	FAILED: Symbol(5),
	FINISH: Symbol(6)
})