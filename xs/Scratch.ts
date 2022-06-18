import { Emitter, EventMap } from "@skylixgh/elixor-events";

interface E extends EventMap {
    hi: () => void;
}

const e = new Emitter<E>();
const x = e.on("hi", () => {
    console.log("Compositon working");
});

e.emit("hi");
console.log(x);

e.off(x);
e.emit("hi");
