package br.uefs.repository.util;

public interface IPilha {
    

    public int obterTamanho();

    public boolean estaVazia();
    
    public Object removerTopo();
    
    public void inserirTopo(Object obj);
    
    public Object recuperarTopo();
}

