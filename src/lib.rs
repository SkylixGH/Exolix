pub fn hi<'a>() -> &'a str {
    let hello = "World";
    return hello;
}

pub fn hi_proxy() {
    hi();
}
