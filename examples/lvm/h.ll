@variable = global i32 16

define i32 @main() {
    %1 = load i32, i32* @variable  ; load the global variable
    %2 = mul i32 %1, 2
    store i32 %2, i32* @variable   ; store instruction to write to global variable
    ret i32 %2
}

define i32 @print(i32 %x) {
    %1 = call i32 (i32*, ...)* @printf(i8* getelementptr inbounds ([7 x i32], [7 x i32]* @.str, i32 0, i32 0), i32 %x)
    ret i32 %1
}