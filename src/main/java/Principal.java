
import com.entidade.Cliente;
import com.fasterxml.jackson.core.JsonProcessingException;
import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import com.fasterxml.jackson.databind.ObjectMapper;
import javax.swing.JOptionPane;
import javax.ws.rs.client.Entity;

/**
 *
 * @author osmar
 */
public class Principal {

    public static void main(String args[]) throws JsonProcessingException {

        //Url do serviço
        String url = "http://localhost:8080/webservice_cliente_jaxrs_rest/rest/cliente";

        //Criar instância do cliente JAX-RS
        Client clienteJAXRS = ClientBuilder.newClient();

        //Criar o parser do JSON para o objeto Mensagem
        ObjectMapper objectMapper = new ObjectMapper();

        int opcao = -1;
        while (opcao != 9) {
            //Menu de opções
            opcao = Integer.parseInt(JOptionPane.showInputDialog("Menu Cliente: \n1 - getLista \n2 - getCliente \n3 - Inserir \n4 - Alterar \n5 - Excluir \n9 - Sair"));

            //Opção getLista
            if (opcao == 1) {
                //Requisição GET do serviço        
                Response resposta = clienteJAXRS.target(url).request(MediaType.APPLICATION_JSON).get();

                // Verificar o código de resposta
                if (resposta.getStatus() == 200) {
                    //Resposta bruta do servidor
                    String jsonResposta = resposta.readEntity(String.class);

                    System.out.println("Texto resposta do servidor: " + jsonResposta);

                } else {
                    System.out.println("Falha ao obter a cliente. Código de resposta: " + resposta.getStatus());
                }

            } else {
                //Opção getCliente
                if (opcao == 2) {
                    String clienteId = JOptionPane.showInputDialog("Digite o código do cliente");

                    //Requisição GET do serviço        
                    Response resposta = clienteJAXRS.target(url + "/" + clienteId).request(MediaType.APPLICATION_JSON).get();

                    // Verificar o código de resposta
                    if (resposta.getStatus() == 200) {
                        //Resposta bruta do servidor
                        String jsonResposta = resposta.readEntity(String.class);
                        //System.out.println("Texto resposta do servidor: " + jsonResposta);

                        //Preenche os objeto mensagem com os dados do JSON
                        Cliente cliente = objectMapper.readValue(jsonResposta, Cliente.class);
                        //Mostra os dados do objeto
                        System.out.println("Dados do objeto Cliente:" + cliente);

                    } else {
                        System.out.println("Falha ao obter a cliente. Código de resposta: " + resposta.getStatus());
                    }
                } else {
                    //Opção inserir
                    if (opcao == 3) {

                        //Requisição POST do serviço        
                        String clienteId = JOptionPane.showInputDialog("Digite o código do cliente");
                        String nome = JOptionPane.showInputDialog("Digite o nome do cliente");
                        String cpf = JOptionPane.showInputDialog("Digite o cpf do cliente");

                        //Instancia o objeto cliente 
                        Cliente cliente = new Cliente(clienteId, nome, cpf);

                        // Converter o objeto cliente para uma string JSON
                        String jsonInputString = objectMapper.writeValueAsString(cliente);
                        //System.out.println("jsonstring:" + jsonInputString);

                        // Enviar a requisição POST
                        Response resposta = clienteJAXRS.target(url).request(MediaType.APPLICATION_JSON).post(Entity.json(jsonInputString));
                        //System.out.println("resposta:" + resposta);

                        // Verificar o código de resposta
                        if (resposta.getStatus() == 200) {
                            System.out.println("Cliente criado com sucesso!");
                            System.out.println("Resposta do servidor: " + resposta.readEntity(String.class));
                        } else {
                            System.out.println("Falha ao inserir o cliente. Código de resposta: " + resposta.getStatus());
                        }
                    } else {
                        if (opcao == 4) {
                            //Requisição PUT do serviço        
                            String clienteId = JOptionPane.showInputDialog("Digite o código do cliente a ser alterado");
                            String nome = JOptionPane.showInputDialog("Digite o novo nome do cliente");
                            String cpf = JOptionPane.showInputDialog("Digite o novo cpf do cliente");

                            //Instancia o objeto cliente 
                            Cliente cliente = new Cliente(clienteId, nome, cpf);

                            // Converter o objeto cliente para uma string JSON
                            String jsonInputString = objectMapper.writeValueAsString(cliente);
                            //System.out.println("jsonstring:" + jsonInputString);

                            // Enviar a requisição PUT
                            Response resposta = clienteJAXRS.target(url).request(MediaType.APPLICATION_JSON).put(Entity.json(jsonInputString));
                            //System.out.println("resposta:" + resposta);

                            // Verificar o código de resposta
                            if (resposta.getStatus() == 200) {
                                System.out.println("Cliente alterado com sucesso!");
                                System.out.println("Resposta do servidor: " + resposta.readEntity(String.class));
                            } else {
                                System.out.println("Falha ao alterar o cliente. Código de resposta: " + resposta.getStatus());
                            }

                        } else {
                            if (opcao == 5) {
                                //Requisição PUT do serviço        
                                String clienteId = JOptionPane.showInputDialog("Digite o código do cliente a ser excluído");

                                // Enviar a requisição DELETE
                                Response resposta = clienteJAXRS.target(url + "/" + clienteId).request(MediaType.APPLICATION_JSON).delete();
                                //System.out.println("resposta:" + resposta);

                                // Verificar o código de resposta
                                if (resposta.getStatus() == 200) {
                                    System.out.println("Cliente excluído com sucesso!");
                                    System.out.println("Resposta do servidor: " + resposta.readEntity(String.class));
                                } else {
                                    System.out.println("Falha ao excluir o cliente. Código de resposta: " + resposta.getStatus());
                                }
                            }
                        }
                    }
                }
            }
        }
        //Fechar o cliente
        clienteJAXRS.close();
    }
}
