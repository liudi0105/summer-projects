export type AppPageParam = {
  pageSize: number;
  pageIndex: number;
};

export type AppPageResult<T> = {
  totalElements: number;
  content: T[];
};
