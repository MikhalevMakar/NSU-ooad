package ru.nsu.ooad.communicationboost.abilitycore.security.bdmodel.entity;

import org.springframework.data.annotation.Id;
import org.springframework.data.redis.core.RedisHash;

import java.io.Serializable;

@RedisHash("secret_code")
public record SecretCode(@Id String username, int code) implements Serializable {}
