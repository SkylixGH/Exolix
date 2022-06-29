use elixor::{hi, hi_proxy};

fn main() {
    bb(hi());
    hi();

    hi_proxy();
}

fn bb(ar: &str) {
    println!("{}", ar);
}
