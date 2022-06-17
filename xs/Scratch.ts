import { Emitter } from "@skylixgh/elixor-events";

const e = new Emitter();
const x = e.on("hi", () => {
    console.log("Compositon working");
});

e.emit("hi");
console.log(x);

e.off(x);
e.emit("hi");
