import { Button, ButtonProps } from "antd";

export type LButtonProps = {} & ButtonProps;

export const LButton = (props: LButtonProps) => {
  return <Button>{props.children}</Button>;
};
