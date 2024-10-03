export type AppPageParam = {
  pageSize: number;
  pageIndex: number;
};

export type AppPageResult<T> = {
  content: T[];
} & AppPageParam;
