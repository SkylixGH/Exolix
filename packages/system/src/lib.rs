use neon::prelude::*;
use is_elevated::is_elevated;

fn get_thread_count(mut cx: FunctionContext) -> JsResult<JsNumber> {
    Ok(cx.number(num_cpus::get() as f64))
}

fn win32_is_process_elevated(mut cx: FunctionContext) -> JsResult<JsBoolean> {
    Ok(cx.boolean(is_elevated()))
}

#[neon::main]
fn main(mut cx: ModuleContext) -> NeonResult<()> {
    cx.export_function("getThreadCount", get_thread_count)?;
    cx.export_function("win32IsProcessElevated", win32_is_process_elevated)?;

    Ok(())
}
