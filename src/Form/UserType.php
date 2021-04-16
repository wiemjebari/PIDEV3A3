<?php

namespace App\Form;

use App\Entity\User;
use Symfony\Component\Form\AbstractType;
use Symfony\Component\Form\Extension\Core\Type\TextType;
use Symfony\Component\Form\Extension\Core\Type\DateType;
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
                    'placeholder'=>'nom d utilisateur',
                    'class'=>'name'
                ]
            ])
            ->add('prenom', TextType::class,[
                'label'=>'Prenom du client',
                'attr'=>[
                    'placeholder'=>'prenom d utilisateur',
                    'class'=>'name'
                ]
            ])
            ->add('sexe', ChoiceType::class, array(
                'choices' => array('H' => 'Homme', 'F' => 'Femme'),
            ))
            ->add('date_naissance', DateType::class, [
                'widget' => 'choice',
            ])
            ->add('mail', TextType::class,[
                'label'=>'Mail du client',
                'attr'=>[
                    'placeholder'=>'mail d utilisateur',
                    'class'=>'name'
                ]
            ])
            ->add('adresse', TextType::class,[
                'label'=>'@ du client',
                'attr'=>[
                    'placeholder'=>'adresse d utilisateur',
                    'class'=>'name'
                ]
            ])
            ->add('role', ChoiceType::class, array(
                'choices' => array('Admin' => 'Admin', 'Client' => 'Client'),
            ))
            ->add('password', PasswordType::class,[
                'label'=>'Password du client',
                'attr'=>[
                    'placeholder'=>'password d utilisateur',
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
