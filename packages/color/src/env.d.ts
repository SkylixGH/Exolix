declare module 'nearest-color' {
    type compare = (color: string) => string | any;

    const ex: {
        from: (colors: { [key: string]: string }) => compare
    };

    export = ex;
}
