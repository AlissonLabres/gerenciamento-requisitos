package br.com.backend.requisitos.enums;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public enum StatusAtividade
{
  HAINICIAR(Integer.valueOf(0), "Ha iniciar"), 
  DESENVOLVENDO(Integer.valueOf(1), "Desenvolvendo"), 
  TESTANDO(Integer.valueOf(2), "Testando"), 
  PARADO(Integer.valueOf(3), "Parado"), 
  CONCLUIDO(Integer.valueOf(4), "Concluido");
  
  private final Integer number;
  private final String value;
  
  private StatusAtividade(Integer number, String value) {
    this.number = number;
    this.value = value;
  }
  
  public String getValue() {
    return value;
  }
  
  public Integer getNumber() {
    return number;
  }
  
  public String toString()
  {
    return value;
  }
  
  public static StatusAtividade valueString(String _status) {
    StatusAtividade statusEnum = null;
    
    for (StatusAtividade _statusString : values()) {
      if (_statusString.getValue().equals(_status)) { return statusEnum = _statusString;
      }
    }
    return statusEnum;
  }
  
  public static Map<StatusAtividade, String> getPerfis() {
    Map<StatusAtividade, String> map = new ConcurrentHashMap<StatusAtividade, String>();
    for (StatusAtividade userType : values()) {
      map.put(userType, userType.toString());
    }
    return map;
  }
  
  public static StatusAtividade getPerfil(int codigoInt)
  {
    StatusAtividade StatusEnum = null;
    
    for (StatusAtividade _statusNumber : values()) {
      if (_statusNumber.getNumber().intValue() == codigoInt) { return StatusEnum = _statusNumber;
      }
    }
    return StatusEnum;
  }
}
