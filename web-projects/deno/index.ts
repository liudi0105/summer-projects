const { test: denoTest } = Deno;

export const test = (name: string, fn: () => void) => {
  denoTest({ name, fn });
};

denoTest({
  name: "test list",
  fn() {},
});
