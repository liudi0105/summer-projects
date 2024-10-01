import * as Ant from "antd";

export type TableProps = {
} & Ant.TableProps;

export const Table = (props: TableProps) => {

  const { rowKey = 'id' } = props

  return (
    <ProTable
      rowKey={rowKey}
      {...props}
    ></ProTable>
  );
};
