use ratatui;
use ratatui::{DefaultTerminal, Frame};

pub struct Term {
    term: DefaultTerminal,
}

impl Term {
    pub fn new() -> DefaultTerminal {
        ratatui::init()
    }

    pub fn restore() {
        ratatui::restore();
    }

    pub fn render(frame: &mut Frame) {
        frame.render_widget("hello world", frame.area());
    }
}
