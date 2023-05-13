package com.entidade;

/**
 * Classe que representa a abstração principal do sistema.
 *
 * @author osmarbraz
 */
public class Cliente {

    /**
     * Serve para identificar um cliente.
     */
    private String clienteId;
    /**
     * Nome do Cliente.
     */
    private String nome;
    /**
     * CPF do cliente
     */
    private String cpf;

    /**
     * Construtor sem argumentos da classe.
     */
    public Cliente() {
        this("", "", "");
    }

    /**
     * Construtor com argumentos da classe.
     *
     * @param clienteId
     * @param nome
     * @param cpf
     */
    public Cliente(String clienteId, String nome, String cpf) {
        setClienteId(clienteId);
        setNome(nome);
        setCpf(cpf);
    }

    /**
     * Retorna o id de um cliente.
     *
     * @return Uma string com o Id
     */
    public String getClienteId() {
        return clienteId;
    }

    /**
     * Modifica o id de um cliente.
     *
     * @param clienteId Um literal com o id de um cliente.
     */
    public void setClienteId(String clienteId) {
        this.clienteId = clienteId;
    }

    public void setClienteId(int clienteId) {
        setClienteId(clienteId + "");
    }

    /**
     * Retorna o nome de um cliente.
     *
     * @return Uma string com o nome
     */
    public String getNome() {
        return nome;
    }

    /**
     * Modifica o nome de um cliente.
     *
     * @param nome Um literal com o nome de um cliente.
     */
    public void setNome(String nome) {
        this.nome = nome;
    }

    /**
     * Retorna o cpf de um cliente.
     *
     * @return Uma string com o cpf
     */
    public String getCpf() {
        return cpf;
    }

    /**
     * Modifica o CPF de um cliente.
     *
     * @param cpf Um literal com o cpf de um cliente
     */
    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    /**
     * Retorna uma string com o estado do objeto.
     */
    @Override
    public String toString() {
        return ("clienteId:" + getClienteId() + " - Nome :" + getNome() + " - CPF :" + getCpf());
    }
}
