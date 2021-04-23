<?php

namespace App\Form;

use App\Entity\User;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\PasswordType;
use Symfony\Bridge\Doctrine\Form\Type\EntityType;
use Symfony\Component\Form\Extension\Core\Type\ChoiceType;
use Symfony\Component\Form\Extension\Core\ChoiceList\ChoiceList;
use Symfony\Component\Form\FormBuilderInterface;
use Symfony\Component\OptionsResolver\OptionsResolver;

class UserType extends AbstractType
{
    public function buildForm(FormBuilderInterface $builder, array $options)
    {
        $builder
            ->add('nom', TextType::class,[
                'label'=>'Nom du client',
                'attr'=>[
                    'placeholder'=>'Merci de définir le nom du client',
                    'class'=>'name'
                ]
            ])
            ->add('prenom', TextType::class,[
                'label'=>'Prenom du client',
                'attr'=>[
                    'placeholder'=>'Merci de définir le prenom du client',
                    'class'=>'name'
                ]
            ])
            ->add('sexe', ChoiceType::class, array(
                'choices' => array('H' => 'Homme', 'F' => 'Femme'),
            ))
            ->add('date_naissance', TextType::class,[
                'label'=>'Date de naissance du client',
                'attr'=>[
                    'placeholder'=>'Merci de définir la date de naissance du client',
                    'class'=>'name'
                ]
            ])
            ->add('mail', TextType::class,[
                'label'=>'Mail du client',
                'attr'=>[
                    'placeholder'=>'Merci de définir le mail du client',
                    'class'=>'name'
                ]
            ])
            ->add('adresse', TextType::class,[
                'label'=>'@ du client',
                'attr'=>[
                    'placeholder'=>'Merci de définir l adresse du client',
                    'class'=>'name'
                ]
            ])
            ->add('role', ChoiceType::class, array(
                'choices' => array('Admin' => 'Admin', 'Client' => 'Client'),
            ))
            ->add('password', PasswordType::class,[
                'label'=>'Password du client',
                'attr'=>[
                    'placeholder'=>'Merci de définir le password du client',
                    'class'=>'name'
                ]
            ])
        ;
    }

    public function configureOptions(OptionsResolver $resolver)
    {
        $resolver->setDefaults([
            'data_class' => User::class,
        ]);
    }
}
