package ru.nsu.ooad.communicationboost.scenarios.modelcontract.telegramdto;

import java.io.Serializable;
import java.util.List;

public record Button(List<String> text) implements Serializable {}
//TODO: что именно будет в этой dtoшке нужно обсудить, точнее изучить api кнопки-tg
