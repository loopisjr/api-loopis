package br.edu.ifpb.apiloopis.emails;

import br.edu.ifpb.apiloopis.entities.Evento;
import br.edu.ifpb.apiloopis.entities.Funcionario;

import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class GerenciadorEmails {

    public GerenciadorEmails(){}

    public void preparaEmail(Evento evento) {

        String titulo = "Lembrete - Novo evento da Loopis Jr";
        String corpo = "Gostaríamos de informá-lo sobre um novo evento da empresa Loopis Jr," +
                " onde você deve participar. \n" +
                "Título do evento: "+ evento.getTitulo() +
                "\n Descrição: " + evento.getDescricao() +
                "\n Data e hora: " + evento.getData().toString() + ", as "+ evento.getHora() + ".";

        List<String> emails = new ArrayList<>();
        evento.getFuncionariosEnvolvidos().forEach(f-> emails.add(f.getEmail()));
        if (!emails.isEmpty()) {
            String destinatarios = "";
            for (String e : emails) {
                destinatarios += e + ",";
            }
            enviar(destinatarios.substring(0, destinatarios.length() - 1), titulo,corpo);
        }
    }

    private void enviar(String destinatario, String titulo, String corpo) {
        Properties props = new Properties();
        props.put("mail.smtp.host", "smtp.gmail.com");
        props.put("mail.smtp.socketFactory.port", "465");
        props.put("mail.smtp.socketFactory.class",
                "javax.net.ssl.SSLSocketFactory");
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.port", "465");

        Session session = Session.getInstance(props,
                new Authenticator() {
                    @Override
                    protected PasswordAuthentication getPasswordAuthentication() {
                        return new PasswordAuthentication("dac20181@gmail.com",
                                "D2!dac2018");
                    }
                });
        session.setDebug(true);
        try {
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress("dac20181@gmail.com"));

            Address[] toUser = InternetAddress.parse(destinatario);

            message.setRecipients(Message.RecipientType.TO, toUser);
            message.setSubject(titulo);
            message.setText(corpo);

            Transport.send(message);

            System.out.println("Feito!!!");

        } catch (MessagingException e) {
            throw new RuntimeException(e);
        }
    }
}
