import { styled } from "@common-module/common-react";
import { Button as AntButton, ButtonProps } from "antd";

export type LButtonProps = {} & ButtonProps;

const SMenuButton = styled(AntButton)`
  width: 100%;
  height: 100%;
  text-align: start;
`;

export const MenuButton = (props: LButtonProps) => {
  return <SMenuButton type="link" {...props}></SMenuButton>;
};

export const Button = (props: LButtonProps) => {
  return <AntButton {...props} />;
};
