import { Modal, ModalProps } from "antd";
import { ReactNode, useState } from "react";

export type TriggerModalProps = {
  trigger: ReactNode;
} & ModalProps;

export const TriggerModal = (props: TriggerModalProps) => {
  const { trigger, onCancel = () => {} } = props;

  const [open, setOpen] = useState(false);

  return (
    <>
      <div onClick={() => setOpen(true)}>{trigger}</div>
      <Modal
        {...props}
        open={open}
        onCancel={(e) => {
          setOpen(false);
          onCancel(e);
        }}
      ></Modal>
    </>
  );
};
