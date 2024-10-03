import { ProTable, ProTableProps } from "@ant-design/pro-components";

export type TableProps<DataSource, U> = {
} & ProTableProps<DataSource, U>;

export const Table = (props: TableProps) => {

  const { rowKey = 'id' } = props

  return (
    <ProTable
      rowKey={rowKey}
      {...props}
    ></ProTable>
  );
};
